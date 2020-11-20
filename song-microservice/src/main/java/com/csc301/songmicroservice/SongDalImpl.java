package com.csc301.songmicroservice;

import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Map;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Pattern;

import javax.xml.ws.spi.http.HttpExchange;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;


@Repository
public class SongDalImpl implements SongDal {

	private final MongoTemplate db;

	@Autowired
	public SongDalImpl(MongoTemplate mongoTemplate) {
		this.db = mongoTemplate;
	}

	@Override
	public DbQueryStatus addSong(Song songToAdd) {

		MongoCollection<Document> collection = db.getCollection("songs");
        JSONObject obj = new JSONObject();
        JSONObject rep = new JSONObject();
        String message;
       
        if((songToAdd.getSongName() == null) || (songToAdd.getSongArtistFullName() == null) || (songToAdd.getSongAlbum() == null)) {
        	message = "Error";
        	DbQueryStatus status = new DbQueryStatus(message, DbQueryExecResult.QUERY_ERROR_NOT_FOUND);
        	return status;
        }
        
        
       try{
        	obj.put("songname", songToAdd.getSongName());
        	obj.put("songFullArtist", songToAdd.getSongArtistFullName());
    		obj.put("songAlbum", songToAdd.getSongAlbum());
    		obj.put("songAmountFavourites", "0");
    		Document doc = Document.parse(obj.toString());
    		collection.insertOne(doc);
    		rep.put("_id", doc.get("_id"));
    		message = rep.toString(); 
    		System.out.println(doc.get("_id"));
    		DbQueryStatus status = new DbQueryStatus(message, DbQueryExecResult.QUERY_OK);
    		return status;   		
        }catch (Exception e) {
        	message = "Error";
        	DbQueryStatus status = new DbQueryStatus(message, DbQueryExecResult.QUERY_ERROR_NOT_FOUND);
        	return status;
        }
	}

	@Override
	public DbQueryStatus findSongById(String songId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DbQueryStatus getSongTitleById(String songId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DbQueryStatus deleteSongById(String songId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DbQueryStatus updateSongFavouritesCount(String songId, boolean shouldDecrement) {
		// TODO Auto-generated method stub
		return null;
	}
}