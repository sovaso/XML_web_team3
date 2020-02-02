package com.example.xml.team3.repository;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.example.xml.team3.model.user.UserPub;
import com.example.xml.team3.util.Authentication.AuthenticationUtilities;
import com.example.xml.team3.util.Exist.ExistRetrieve;
import com.example.xml.team3.util.Exist.ExistStore;
import com.example.xml.team3.util.Exist.ExistUpdate;
import com.example.xml.team3.util.Template.XUpdateTemplate;
import com.example.xml.team3.util.jaxb.MarshallerUtil;
import com.example.xml.team3.util.jaxb.UnmarshallerUtil;

@Repository
public class UserRepository {

	private static String userCollection = "/db/team3/user";

	@Autowired
	ExistRetrieve existRetrieve;

	@Autowired
	ExistStore existStore;

	@Autowired
	ExistUpdate existUpdate;

	@Autowired
	AuthenticationUtilities connectionPool;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UnmarshallerUtil unmarshallerUtil;

	@Autowired
	MarshallerUtil marshallerUtil;

	public String saveUser(UserPub user) throws Exception {
		String userXML = marshallerUtil.marshallUser(user);
		String userId = user.getPassword();
		ExistStore.store(userCollection, userId, userXML);
		return userId;
	}

	public UserPub getByUsername(String username) {
		String xQuery = "//userPub[username=\"" + username + "\"" + "]";
		//String xQuery = "//user/userPub[username[text()='bbb']]";
		//String xQuery = "collection(\"user\")/userPub";
		//String xQuery = username;
		//ResourceSet result = queryService.query("//Users/User[./Roles/Role[text()='ROLE_USER']]");
		UserPub user = null;
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(userCollection,
					xQuery, XUpdateTemplate.TARGET_NAMESPACE + "/user");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					user = unmarshallerUtil.unmarshallUser(((XMLResource) res).getContent().toString());
					break;
				} finally {
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException xe) {
						xe.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public void deleteUser(String username) {
		try {
			ExistUpdate.delete(userCollection, username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
