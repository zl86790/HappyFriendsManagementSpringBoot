package name.lizhe.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import name.lizhe.user.bean.User;
import name.lizhe.user.exception.UserNotFoundException;
import name.lizhe.user.service.UserService;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

	@RequestMapping(value = "/getFriends", method = RequestMethod.POST, consumes = "application/json")
	public Map hello(@RequestBody Map map) {

		Map result = new HashMap();
		String mailAdress = map.get("email").toString();

		UserService userService = UserService.getInstance();
		List<String> friends;
		try {
			friends = userService.getFriends(new User(mailAdress));
			result.put("success", true);
			result.put("friends", friends);
			result.put("count", friends.size());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/getRecipients", method = RequestMethod.POST, consumes = "application/json")
	public Map getRecipients(@RequestBody Map map) {
		Map result = new HashMap();
		UserService userService = UserService.getInstance();

		String mailAdress = map.get("sender").toString();
		List<String> recipients = null;
		try {
			recipients = userService.getRecipients(new User(mailAdress));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		result.put("success", true);
		result.put("recipients", recipients);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public Map create(@RequestBody Map map) {
		UserService userService = UserService.getInstance();
		Map result = new HashMap();

		List array = (List) map.get("friends");
		boolean success = false;
		try {
			success = userService.create(new User(array.get(0).toString()), new User(array.get(1).toString()));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (name.lizhe.user.exception.CreateConflictException e) {
			e.printStackTrace();
		}
		result.put("success", success);
		
		return result;

	}
	
	@RequestMapping(value = "/getCommonFriends", method = RequestMethod.POST, consumes = "application/json")
	public Map getCommonFriends(@RequestBody Map map) {
		UserService userService = UserService.getInstance();
		Map result = new HashMap();

		List array = (List) map.get("friends");
		
		List<String> friends1 = null;
		List<String> friends2 = null;
		try {
			friends1 = userService.getFriends(new User(array.get(0).toString()));
			friends2 = userService.getFriends(new User(array.get(1).toString()));
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> commonFriends = userService.getCommonFriends(friends1,friends2);
		
		result.put("success", true);
		result.put("friends", commonFriends);
		result.put("count", commonFriends.size());
		
		return result;

	}
	
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST, consumes = "application/json")
	public Map subscribe(@RequestBody Map map) {
		UserService userService = UserService.getInstance();
		Map result = new HashMap();

		String requestor = map.get("requestor").toString();
		String target = map.get("target").toString();
		
		boolean success = false;
		try {
			success = userService.subscribe(new User(requestor),new User(target));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		
		result.put("success", success);
		return result;

	}
	
	@RequestMapping(value = "/block", method = RequestMethod.POST, consumes = "application/json")
	public Map block(@RequestBody Map map) {
		UserService userService = UserService.getInstance();
		Map result = new HashMap();

		String requestor = map.get("requestor").toString();
		String target = map.get("target").toString();
		
		boolean success = false;
		try {
			success = userService.block(new User(requestor),new User(target));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		
		result.put("success", success);
		return result;

	}

}