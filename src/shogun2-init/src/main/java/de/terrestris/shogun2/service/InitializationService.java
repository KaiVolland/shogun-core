package de.terrestris.shogun2.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.terrestris.shogun2.dao.ApplicationDao;
import de.terrestris.shogun2.dao.LayoutDao;
import de.terrestris.shogun2.dao.ModuleDao;
import de.terrestris.shogun2.dao.RoleDao;
import de.terrestris.shogun2.dao.UserDao;
import de.terrestris.shogun2.dao.UserGroupDao;
import de.terrestris.shogun2.init.ContentInitializer;
import de.terrestris.shogun2.model.Application;
import de.terrestris.shogun2.model.Role;
import de.terrestris.shogun2.model.User;
import de.terrestris.shogun2.model.UserGroup;
import de.terrestris.shogun2.model.layout.Layout;
import de.terrestris.shogun2.model.module.Module;

/**
 * This service class will be used by the {@link ContentInitializer} to create content
 * on initialization. The methods of this service are not secured, which is
 * required, because the ACL mechanism would deny access to the
 * secured methods of the {@link AbstractCrudService}.
 *
 * @author Nils Bühner
 *
 */
@Service("initializationService")
@Transactional(value="transactionManager")
public class InitializationService {

	/**
	 * The Logger
	 */
	private static final Logger LOG = Logger
			.getLogger(InitializationService.class);

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserGroupDao userGroupDao;

	@Autowired
	private LayoutDao layoutDao;

	@Autowired
	private ModuleDao moduleDao;

	@Autowired
	private ApplicationDao applicationDao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	/**
	 * Used to create a role.
	 *
	 * @param role
	 * @return
	 */
	public Role createRole(Role role) {
		roleDao.saveOrUpdate(role);
		LOG.debug("Created the role " + role);
		return role;
	}

	/**
	 * Used to create a user.
	 *
	 * @param user
	 * @return
	 */
	public User createUser(User user) {
		// encode the raw password using bcrypt
		final String pwHash = bcrypt.encode(user.getPassword());
		user.setPassword(pwHash);
		userDao.saveOrUpdate(user);
		LOG.debug("Created the user " + user);
		return user;
	}

	/**
	 * Used to create a user.
	 *
	 * @param userGroup
	 * @return
	 */
	public UserGroup createUserGroup(UserGroup userGroup) {
		userGroupDao.saveOrUpdate(userGroup);
		LOG.debug("Created the user group " + userGroup);
		return userGroup;
	}

	/**
	 * Used to create a layout.
	 *
	 * @param layout
	 */
	public Layout createLayout(Layout layout) {
		layoutDao.saveOrUpdate(layout);
		LOG.debug("Created the layout " + layout);
		return layout;
	}

	/**
	 * Used to create a module.
	 *
	 * @param module
	 */
	public Module createModule(Module module) {
		moduleDao.saveOrUpdate(module);
		LOG.debug("Created the module " + module);
		return module;
	}

	/**
	 * Used to create an application.
	 *
	 * @param application
	 * @return
	 */
	public Application createApplication(Application application) {
		applicationDao.saveOrUpdate(application);
		LOG.debug("Created the application " + application);
		return application;
	}

}
