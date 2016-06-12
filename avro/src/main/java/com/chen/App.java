package com.chen;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import example.avro.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
//    	User user1 = new User();
//    	User user2 = User.newBuilder()
//    			.setName("Fang")
//    			.setFavoriteColor("blue")
//    			.setFavoriteNumber(null)
//    			.build();
//    	user1.setName("Chen Xie");
//    	user1.setFavoriteNumber(255);
//    	
//    	DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
//    	DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
//    	dataFileWriter.create(user1.getSchema(), new File("users.avro"));
//    	dataFileWriter.append(user1);
//    	dataFileWriter.append(user2);
//    	dataFileWriter.close();
    	
    	// Deserialize Users from disk
    	DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
    	DataFileReader<User> dataFileReader = new DataFileReader<User>(new File("users.avro"), userDatumReader);
    	User user = null;
    	while (dataFileReader.hasNext()) {
    	// Reuse user object by passing it to next(). This saves us from
    	// allocating and garbage collecting many objects for files with
    	// many items.
    	user = dataFileReader.next(user);
    	System.out.println(user);
    	}
    	dataFileReader.close();
    }
}
