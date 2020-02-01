package com.example.xml.team3.util.Exist;

import org.springframework.stereotype.Component;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XUpdateQueryService;

import com.example.xml.team3.util.Authentication.AuthenticationUtilities;

@Component
public class ExistUpdate {
	public static long update(String collectionId, String documentId, String contextXPath, String xmlFragment,
			String updateTemplate) throws Exception {
		long mods = 0;
		Collection col = AuthenticationUtilities.initDBCollection(collectionId);
		try {
			XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
			xupdateService.setProperty("indent", "yes");
			String command = String.format(updateTemplate, contextXPath, xmlFragment);
			mods = xupdateService.updateResource(documentId, command);
			return mods;
		} finally {
			if (col != null) {
				try {
					col.close();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
	}

	public static long delete(String collectionId, String documentId) throws Exception {
		long mods = 0;
		Collection col = AuthenticationUtilities.initDBCollection(collectionId);
		try {
			Resource res = col.getResource(documentId);
			col.removeResource(res);
			mods = 1;
			return mods;
		} finally {
			if (col != null) {
				try {
					col.close();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
	}
}
