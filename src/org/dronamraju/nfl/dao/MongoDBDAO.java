package org.dronamraju.nfl.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;

import org.bson.types.ObjectId;
import org.dronamraju.nfl.converter.PersonConverter;
import org.dronamraju.nfl.model.Game;
import org.dronamraju.nfl.model.Person;
import org.dronamraju.nfl.model.User;

import static com.mongodb.client.model.Filters.*;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBDAO {
	private static Log log = LogFactory.getLog(MongoDBDAO.class);
	private DBCollection col;
	MongoClient mongoClient = getMongoClient();
	MongoDatabase manudrDatabase = getManudrDatabase();
	MongoCollection<Document> userCollection = getManudrDatabase().getCollection("users");
	MongoCollection<Document> scoreCollection = getManudrDatabase().getCollection("scores");
	MongoCollection<Document> gameCollection = getManudrDatabase().getCollection("games");


	public MongoDBDAO() {
	}

	public MongoClient getMongoClient() {
		MongoClientURI uri = new MongoClientURI("mongodb://manudr:manudr@ds015508.mongolab.com:15508/manudr");
		mongoClient = new MongoClient(uri);
		return mongoClient;
	}

	public MongoDatabase getManudrDatabase() {
		return mongoClient.getDatabase("manudr");
	}

	public User createUser(User user) {
		log.info("createUser()...");
		Document userDocument = new Document("firstName", user.getFirstName())
				.append("lastName", user.getLastName())
				.append("email", user.getEmail())
				.append("password", user.getPassword())
				.append("totalPoints", user.getTotalPoints())
				.append("availablePoints", user.getAvailablePoints())
				.append("isAdmin", user.isAdmin());
		userCollection.insertOne(userDocument);
		log.info("user collection: " + userCollection);
		return user;
	}

	public List<User> readAllUsers() {
		List<User> users = new ArrayList<User>();
	    log.info("collection.count(): " + userCollection.count());
		MongoCursor<Document> cursor = userCollection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				log.info("document: " + document);
				users.add(new User(document.get("_id").toString(),
						document.get("firstName").toString(),
						document.get("lastName").toString(),
						document.get("email").toString(),
						document.get("totalPoints").toString(),
						document.get("availablePoints").toString(),
						document.get("password").toString(),
						new Boolean(document.get("isAdmin").toString())));
			}
		} finally {
			cursor.close();
		}
		log.info("users: " + users);
		return users;
	}

	public User findUser(String email, String password) {
		log.info("findUser(): " + email + ", " + password);
		Document document = userCollection.find(eq("email", email)).first();
		if (document != null) {
			User user = new User(document.get("_id").toString(),
					document.get("firstName").toString(),
					document.get("lastName").toString(),
					document.get("email").toString(),
					document.get("totalPoints").toString(),
					document.get("availablePoints").toString(),
					document.get("password").toString(),
					new Boolean(document.get("isAdmin").toString()));
			log.info("User: " + user);
			if (user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	public User findUser(String email) {
		log.info("findUser(): " + email);
		Document document = userCollection.find(eq("email", email)).first();
		if (document != null) {
			User user = new User(document.get("_id").toString(),
					document.get("firstName").toString(),
					document.get("lastName").toString(),
					document.get("email").toString(),
					document.get("totalPoints").toString(),
					document.get("availablePoints").toString(),
					document.get("password").toString(),
					new Boolean(document.get("isAdmin").toString()));
			log.info("User: " + user);
			return user;
		}
		return null;
	}

	public void saveScores(User user, Map<String, String[]> paramMap) {
		Document paramMapDocument = new Document();
		paramMapDocument.append("email", user.getEmail());

		for (String key : paramMap.keySet()) {
			String[] paramValues = paramMap.get(key);
			log.info("Key: " + key + ", Value: " + paramValues[0]);
			paramMapDocument.append(key, paramValues[0]);
		}

		scoreCollection.insertOne(paramMapDocument);
		log.info(readAllScores());
	}

	public List readAllScores() {
		List scoreList = new ArrayList();
		log.info("scoreCollection.count(): " + scoreCollection.count());
		MongoCursor<Document> cursor = scoreCollection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				scoreList.add(document);
			}
		} finally {
			cursor.close();
		}
		log.info("scoreList: " + scoreList);
		return scoreList;
	}

	public Person createPerson(Person p) {
		DBObject doc = PersonConverter.toDBObject(p);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		p.setId(id.toString());
		return p;
	}

	public void updatePerson(Person p) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		this.col.update(query, PersonConverter.toDBObject(p));
	}

	public List<Person> readAllPerson() {
		List<Person> data = new ArrayList<Person>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			Person p = PersonConverter.toPerson(doc);
			data.add(p);
		}
		return data;
	}

	public void deletePerson(Person p) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		this.col.remove(query);
	}

	public Person readPerson(Person p) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		DBObject data = this.col.findOne(query);
		return PersonConverter.toPerson(data);
	}

	public List<Game> addGames() {
		List<Game> games = new ArrayList<>();
		List<Document> documents = new ArrayList<>();
		Document gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Panthers")
				.append("date", "09-08-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Colts")
				.append("date", "09-18-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Bengals")
				.append("date", "09-25-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Buccaneers")
				.append("date", "10-02-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Falcons")
				.append("date", "10-09-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Chargers")
				.append("date", "10-13-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Texans")
				.append("date", "10-24-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Chargers")
				.append("date", "10-30-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Raiders")
				.append("date", "11-06-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Saints")
				.append("date", "11-13-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Chiefs")
				.append("date", "11-27-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Jaguars")
				.append("date", "12-04-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Titans")
				.append("date", "12-11-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Patriots")
				.append("date", "12-18-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Chiefs")
				.append("date", "12-25-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameDocument = new Document("team1Name", "Broncos")
				.append("team2Name", "Raiders")
				.append("date", "01-01-2016")
				.append("time", "06:30PM")
				.append("location", "Denver");
		documents.add(gameDocument);

		gameCollection.insertMany(documents);
		return games;
	}

	public void dropUserCollection() {
		userCollection.drop();
	}

	public void dropScoreCollection() {
		scoreCollection.drop();
	}

	public void dropGameCollection() {
		gameCollection.drop();
	}

	public List<Game> readAllGames() {
		List<Game> games = new ArrayList<Game>();
		log.info("gameCollection.count(): " + gameCollection.count());
		MongoCursor<Document> cursor = gameCollection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				games.add(new Game(document.get("_id").toString(),
						document.get("team1Name").toString(),
						document.get("team2Name").toString(),
						document.get("date").toString(),
						document.get("time").toString(),
						document.get("location").toString()));
			}
		} finally {
			cursor.close();
		}
		log.info("games: " + games);
		return games;
	}
}
