package org.dronamraju.nfl.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;

import org.bson.types.ObjectId;
import org.dronamraju.nfl.converter.PersonConverter;
import org.dronamraju.nfl.model.Person;
import org.dronamraju.nfl.model.User;

import static com.mongodb.client.model.Filters.*;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBDAO {
	private static Log log = LogFactory.getLog(MongoDBDAO.class);
	private DBCollection col;
	MongoClient mongoClient;

	public MongoDBDAO() throws UnknownHostException {
		MongoClientURI uri = new MongoClientURI("mongodb://manudr:manudr@ds015508.mongolab.com:15508/manudr");
		mongoClient = new MongoClient(uri);
	}

	public User createUser(User user) {
		cleanDatabase("manudr");
		Document userDocument = new Document("firstName", user.getFirstName())
				.append("lastName", user.getLastName())
				.append("email", user.getEmail())
				.append("password", user.getPassword());
		mongoClient.getDatabase("manudr").getCollection("users").insertOne(userDocument);
		log.info("user collection: " + mongoClient.getDatabase("manudr").getCollection("users"));
		return user;
	}

	public List<User> readAllUsers() {
		List<User> users = new ArrayList<User>();
		MongoCollection<Document> collection = mongoClient.getDatabase("manudr").getCollection("users");
		log.info("collection.count(): " + collection.count());

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				users.add(new User(document.get("_id").toString(),
						document.get("firstName").toString(),
						document.get("lastName").toString(),
						document.get("email").toString(),
						document.get("password").toString()));
			}
		} finally {
			cursor.close();
		}
		log.info("users: " + users);
		return users;
	}

	public void findPersion() {
		MongoCollection<Document> collection = mongoClient.getDatabase("manudr").getCollection("users");
		Document myDoc = collection.find(eq("i", 71)).first();

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

	public void cleanDatabase(String databaseName) {
		mongoClient.dropDatabase(databaseName);
	}

}
