package com.example.xml.team3.util.Exist;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import com.example.xml.team3.util.Authentication.AuthenticationUtilities;
import com.example.xml.team3.util.Authentication.AuthenticationUtilities.ConnectionProperties;

@Component
public class ExistStore {

	private static ConnectionProperties conn;

	public static void store(String collectionId, String documentId, String xmlEntity) throws Exception {
		conn = AuthenticationUtilities.loadProperties();
		if (collectionId.isEmpty() || xmlEntity.isEmpty()) {
			// TREBA EXCEPTION OVDJE
			System.out.println("missing collectionId or documentId or xmlEntity");
		}
		// initialize database driver
		Class<?> cl = Class.forName(conn.driver);
		// encapsulation of the database driver functionality
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		// entry point for the API which enables you to get the Collection reference
		DatabaseManager.registerDatabase(database);
		// a collection of Resources stored within an XML database
		Collection col = null;
		XMLResource res = null;
		try {
			col = getOrCreateCollection(collectionId);
			/*
			 * create new XMLResource with a given id an id is assigned to the new resource
			 * if left empty (null)
			 */
			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
			System.out.println("AAA" + res.getId());
			res.setContent(xmlEntity);
			col.storeResource(res);
		} finally {
			// don't forget to cleanup
			if (res != null) {
				try {
					((EXistResource) res).freeResources();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
			if (col != null) {
				try {
					col.close();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
	}

	private static Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
		return getOrCreateCollection(collectionUri, 0);
	}

	private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {
		Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
		// create the collection if it does not exist
		if (col == null) {
			if (collectionUri.startsWith("/")) {
				collectionUri = collectionUri.substring(1);
			}
			String pathSegments[] = collectionUri.split("/");
			if (pathSegments.length > 0) {
				StringBuilder path = new StringBuilder();
				for (int i = 0; i <= pathSegmentOffset; i++) {
					path.append("/" + pathSegments[i]);
				}
				Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);
				if (startCol == null) {
					String parentPath = path.substring(0, path.lastIndexOf("/"));
					Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user,
							conn.password);
					CollectionManagementService mgt = (CollectionManagementService) parentCol
							.getService("CollectionManagementService", "1.0");
					col = mgt.createCollection(pathSegments[pathSegmentOffset]);
					col.close();
					parentCol.close();
				} else {
					startCol.close();
				}
			}
			return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
		} else {
			return col;
		}
	}
}
