package org.dronamraju.nfl.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.sun.javadoc.Doc;
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
				.append("email", user.getUserName())
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
				users.add(new User(document.get("firstName").toString(),
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
			User user = new User(document.get("firstName").toString(),
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
			User user = new User(document.get("firstName").toString(),
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
		paramMapDocument.append("email", user.getUserName());

		for (String key : paramMap.keySet()) {
			String[] paramValues = paramMap.get(key);
			String value = paramValues[0];
			if (key.contains("gamesForm:")) {
				paramMapDocument.append(key, value);
			}
		}
		scoreCollection.insertOne(paramMapDocument);
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

	public List<Document> getGamesList() {
		List<Document> gamesList = new ArrayList<>();
		MongoCursor<Document> cursor = gameCollection.find().iterator();
		try {
			int i = 1;
			while (cursor.hasNext()) {
				Document document = cursor.next();
				log.info("document: " + document);
				gamesList.add(document);
				i++;
			}
		} finally {
			cursor.close();
		}
		log.info("gamesList: " + gamesList);
		return gamesList;
	}

	public Map<String, Document> getUserGamesMap(String email) {
		Map<String, Document> userGamesMap = new HashMap<>();
		MongoCursor<Document> cursor = scoreCollection.find().iterator();
		try {
			int i = 1;
			while (cursor.hasNext()) {
				Document document = cursor.next();
				log.info(email + ", " + document.get("email"));
				if (email.equals(document.get("email"))) {
					log.info("document: " + document);
					userGamesMap.put(document.get("teamAName_" + i).toString() + document.get("date_" + i), document);
				}
				i++;
			}
			log.info("userGamesMap: " + userGamesMap);
		} finally {
			cursor.close();
		}
		log.info("userGamesMap: " + userGamesMap);
		return userGamesMap;
	}

	public List<Game> readUserGames(String email) {
		List<Game> userGames = readAllGames();
		MongoCursor<Document> cursor = scoreCollection.find().iterator();
		try {
			int i = 1;
			while (cursor.hasNext()) {
				Document document = cursor.next();
				log.info(email + ", " + document.get("email"));
				if (email.equals(document.get("email"))) {
					log.info("document: " + document);
					userGames.add(new Game(document.get("teamAName_" + i) == null ? "" : document.get("teamAName_" + i).toString(),
							document.get("teamBName_" + i) == null ? "" : document.get("teamBName_" + i).toString(),
							document.get("date_" + i) == null ? "" : document.get("date_" + i).toString(),
							document.get("time_" + i) == null ? "" : document.get("time_" + i).toString(),
							document.get("location_" + i) == null ? "" : document.get("location_" + i).toString(),
							document.get("teamAScore_" + i) == null ? "" : document.get("teamAScore_" + i).toString(),
							document.get("teamBScore_" + i) == null ? "" : document.get("teamBScore_" + i).toString(),
							document.get("winningTeam_" + i) == null ? "" : document.get("winningTeam_" + i).toString(),
							document.get("teamsTotalScore_" + i) == null ? "" : document.get("teamsTotalScore_" + i).toString()));
				}
				i++;
			}
		} finally {
			cursor.close();
		}
		log.info("userGames: " + userGames);
		return userGames;
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
		int i = 1;
		Document gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Panthers")
				.append("date_" + i, "09-08-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Colts")
				.append("date_" + i, "09-18-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Bengals")
				.append("date_" + i, "09-25-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Buccaneers")
				.append("date_" + i, "10-02-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Falcons")
				.append("date_" + i, "10-09-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Chargers")
				.append("date_" + i, "10-13-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Texans")
				.append("date_" + i, "10-24-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Chargers")
				.append("date_" + i, "10-30-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Raiders")
				.append("date_" + i, "11-06-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Saints")
				.append("date_" + i, "11-13-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Chiefs")
				.append("date_" + i, "11-27-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Jaguars")
				.append("date_" + i, "12-04-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Titans")
				.append("date_" + i, "12-11-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Patriots")
				.append("date_" + i, "12-18-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Chiefs")
				.append("date_" + i, "12-25-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
		documents.add(gameDocument);
		i++;
		gameDocument = new Document("teamAName_" + i, "Broncos")
				.append("teamBName_" + i, "Raiders")
				.append("date_" + i, "01-01-2016")
				.append("time_" + i, "06:30PM")
				.append("location_" + i, "Denver")
				.append("teamAScore_" + i, "")
				.append("teamBScore_" + i, "")
				.append("winningTeam_" + i, "")
				.append("teamsTotalScore_" + i, "");
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
			int i = 1;
			while (cursor.hasNext()) {
				Document document = cursor.next();
				games.add(new Game(document.get("teamAName_" + i).toString(),
						document.get("teamBName_" + i).toString(),
						document.get("date_" + i).toString(),
						document.get("time_" + i).toString(),
						document.get("location_" + i).toString(),
						document.get("teamAScore_" + i).toString(),
						document.get("teamBScore_" + i).toString(),
						document.get("winningTeam_" + i).toString(),
						document.get("teamsTotalScore_" + i).toString()));
				i++;
			}
		} finally {
			cursor.close();
		}
		log.info("games: " + games);
		return games;
	}
}
