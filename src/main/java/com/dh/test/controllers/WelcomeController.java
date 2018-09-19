package com.dh.test.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dh.test.models.Menu;
import com.dh.test.models.MenuSize;
import com.dh.test.models.MetaStatus;
import com.dh.test.models.Size;
import com.dh.test.models.Store;
import com.dh.test.models.User;
import com.dh.test.repositories.MenuRepository;
import com.dh.test.repositories.MenuSizeRepository;
import com.dh.test.repositories.MetaStatusRepository;
import com.dh.test.repositories.SizeRepository;
import com.opencsv.CSVReader;

@Controller
public class WelcomeController {
	
	@Autowired
	MetaStatusRepository statusRepository;
	@Autowired
	MenuRepository menuRepository;
	@Autowired
	SizeRepository sizeRepository;
	@Autowired
	MenuSizeRepository menuSizeRepository;
	
	@Autowired
    private HttpServletRequest request;
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {	      
      model.put("message", "Hello World");

      return "welcome";
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/secured/board")
	public String board(Map<String, Object> model) {		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//List<Menu> menuList = menuRepository.findBySoreAndStatusOrderBySortOrderAsc(statusRepository.findByCode("STAT001"));
		List<Menu> menuList = menuRepository.findByStoreAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderBySortOrderAsc(
				((User) principal).getStore()
				, statusRepository.findByCode("STAT001")
				, Date.valueOf(java.time.LocalDate.now())
				, Date.valueOf(java.time.LocalDate.now()));
		
		if((int) Math.ceil((double)menuList.size()/(double)9.0) <=0) {
			model.put("size", 1);
		} else {
			model.put("size", (int) Math.ceil((double)menuList.size()/(double)9.0));
		}	
		model.put("store", ((User) principal).getStore());
		model.put("menuList", menuList);
		
		return "board";
	}
	
	@RequestMapping("/secured/menu")
	public String menu(Map<String, Object> model) {		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Menu> menuList = menuRepository.findByStoreAndStatusOrderBySortOrderAsc(
				((User) principal).getStore()
				, statusRepository.findByCode("STAT001"));
		
		List<Size> sizeList = sizeRepository.findByStoreAndStatus(
				((User) principal).getStore()
				, statusRepository.findByCode("STAT001"));
		
		model.put("store", ((User) principal).getStore());
		model.put("sizeList", sizeList);
		model.put("menuList", menuList);
		return "menu";
	}
	
	@RequestMapping("/secured/editMenu")
	public String editMenu(@RequestParam MultipartFile file_edit, @RequestParam String title_edit,
			  @RequestParam String dateRange_edit,
			  @RequestParam long menu_edit, Map<String, Object> model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Menu menu = menuRepository.getOne(menu_edit);
		
		Date startDate = null;
		Date endDate = null;
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date jDate = format.parse(dateRange_edit.split("to")[0].trim());
			startDate = new java.sql.Date(jDate.getTime());
			jDate = format.parse(dateRange_edit.split("to")[1].trim());
			endDate = new java.sql.Date(jDate.getTime());
		}catch (Exception e) {}
		
		String fileName = "-";
		String filePath = "/uploads/";
		String originalFileName = "-";
		
		menu.setMenu(title_edit
				, startDate
				, endDate
				, originalFileName
				, "-"
				, filePath
				, "-");
		
		menuRepository.save(menu);
		
		//
		if (!file_edit.isEmpty()) {
			originalFileName = StringUtils.cleanPath(file_edit.getOriginalFilename());			
			fileName = String.valueOf(menu.getId()) + "." + FilenameUtils.getExtension(originalFileName);
			
            String realPathtoUploads =  request.getServletContext().getRealPath(filePath);
			
			try {
				InputStream is = file_edit.getInputStream();
				
				Files.copy(is, Paths.get(realPathtoUploads + fileName),
				        StandardCopyOption.REPLACE_EXISTING);
			}catch (Exception e) {}
			
			menu.setOriginalFileName(originalFileName);
			menu.setFileName(fileName);
			menuRepository.save(menu);
		}
		//
		
		model.put("store", menu.getStore());
		return "redirect:/secured/menu";
	}
	
	@RequestMapping("/secured/addMenu")
	public String addMenu(@RequestParam MultipartFile file, @RequestParam String title,
			  @RequestParam String dateRange, Map<String, Object> model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Date startDate = null;
		Date endDate = null;
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date jDate = format.parse(dateRange.split("to")[0].trim());
			startDate = new java.sql.Date(jDate.getTime());
			jDate = format.parse(dateRange.split("to")[1].trim());
			endDate = new java.sql.Date(jDate.getTime());
		}catch (Exception e) {}
		
		String fileName = "-";
		String filePath = "/uploads/";
		String originalFileName = "-";
		
		Menu menu = new Menu(title
				, startDate
				, endDate
				, originalFileName
				, "-"
				, filePath
				, "-"
				, ((User) principal).getStore()
				, statusRepository.findByCode("STAT001"));
		
		menuRepository.save(menu);
		
		//
		if (!file.isEmpty()) {
			originalFileName = StringUtils.cleanPath(file.getOriginalFilename());			
			fileName = String.valueOf(menu.getId()) + "." + FilenameUtils.getExtension(originalFileName);
			
            String realPathtoUploads =  request.getServletContext().getRealPath(filePath);
			
			try {
				InputStream is = file.getInputStream();
				
				Files.copy(is, Paths.get(realPathtoUploads + fileName),
				        StandardCopyOption.REPLACE_EXISTING);
			}catch (Exception e) {}
			
			menu.setOriginalFileName(originalFileName);
			menu.setFileName(fileName);
			menuRepository.save(menu);
		}
		//
		
		model.put("store", menu.getStore());
		return "redirect:/secured/menu";
	}
	
	@RequestMapping("/secured/addMenuSize")
	public String addMenuSize(@RequestParam(defaultValue = "-") String price
			, @RequestParam (defaultValue = "false") boolean checked
			, @RequestParam long size
			, @RequestParam long modalMenu) {
		
		Menu menu = menuRepository.getOne(modalMenu);
		Size sizee = sizeRepository.getOne(size);
		
		if(!checked) {
			Optional<MenuSize> optMenuSize = menuSizeRepository.findByMenuAndSizeAndStatus(
					menu
					, sizee
					, statusRepository.findByCode("STAT001"));
			
			if(optMenuSize.isPresent()) {
				MenuSize menuSize = optMenuSize.get();
				menuSize.setStatus(statusRepository.findByCode("STAT002"));
				menuSizeRepository.save(menuSize);
			}
		} else {
			double prc = 0.0;
			if(!price.equals("-")) {
				prc = Double.parseDouble(price);
			}
			MenuSize menuSize = new MenuSize(prc
					, menu
					, sizee
					, statusRepository.findByCode("STAT001"));
			
			menuSizeRepository.save(menuSize);
		}
		
		return "redirect:/secured/menu";
	}
	
	@RequestMapping("/secured/size")
	public String size(Map<String, Object> model) {		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Size> sizeList = sizeRepository.findByStoreAndStatus(
				((User) principal).getStore()
				, statusRepository.findByCode("STAT001"));
		
		model.put("sizeList", sizeList);
		model.put("message", ((User) principal).getStore().getTitle());
		return "size";
	}
	
	@RequestMapping(value = "/secured/addSize"
			, method = RequestMethod.POST
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addSize(String title) throws JSONException {		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
		Size size = new Size(title
				, ((User) principal).getStore()
				, statusRepository.findByCode("STAT001"));
		
		sizeRepository.save(size);
		
		JSONObject json = new JSONObject();
		json.put("id", size.getId());
		json.put("title", size.getTitle());
		json.put("success", true);
		
		return json.toString();
	}
	
	@RequestMapping(value = "/secured/getSize"
			, method = RequestMethod.GET
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getSize(long id) throws JSONException {	
		Menu menu = menuRepository.getOne(id);
		
		List<MenuSize> menuSizeList = menuSizeRepository.findByMenuAndStatus(menu
				, statusRepository.findByCode("STAT001"));
		
		JSONArray menuSizeInfoList = new JSONArray();
		for(MenuSize menuSize: menuSizeList) {
			menuSizeInfoList.put(menuSize.getInfo());
		}
		
		JSONObject json = new JSONObject();
		json.put("menuSizeInfoList", menuSizeInfoList);
		json.put("success", true);
		
		return json.toString();
	}
	
	@RequestMapping(value = "/secured/getMenu"
			, method = RequestMethod.GET
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getMenu(long id) throws JSONException {	
		Menu menu = menuRepository.getOne(id);
		
		JSONObject menuInfo = menu.getInfo();
		
		JSONObject json = new JSONObject();
		json.put("menu", menuInfo);
		json.put("success", true);
		
		return json.toString();
	}
	
	@RequestMapping(value = "/secured/deleteSize"
			, method = RequestMethod.POST
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteSize(long id) throws JSONException {		
		MetaStatus status = statusRepository.findByCode("STAT002");
		Size size = sizeRepository.getOne(id);
		size.setStatus(status);
		sizeRepository.save(size);
		
		try {
			for(MenuSize menuSize : size.getMenuSizeList()) {
				menuSize.setStatus(status);
				menuSizeRepository.save(menuSize);
			}
		} catch(Exception e) {
			
		}
		
		JSONObject json = new JSONObject();
		json.put("success", true);
		
		return json.toString();
	}
	
	@RequestMapping(value = "/secured/deleteMenu"
			, method = RequestMethod.POST
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteMenu(long id) throws JSONException {		
		MetaStatus status = statusRepository.findByCode("STAT002");
		Menu menu = menuRepository.getOne(id);
		menu.setStatus(status);
		menuRepository.save(menu);
		
		try {
			for(MenuSize menuSize : menu.getMenuSizeList()) {
				menuSize.setStatus(status);
				menuSizeRepository.save(menuSize);
			}
		} catch(Exception e) {
			
		}
		
		//TODO
		//disable all related schedule
		
		JSONObject json = new JSONObject();
		json.put("success", true);
		
		return json.toString();
	}
	
	@RequestMapping(value = "/secured/deleteImage"
			, method = RequestMethod.POST
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteImage(long id) throws JSONException {		
		Menu menu = menuRepository.getOne(id);
		menu.setFileName("-");
		menu.setOriginalFileName("-");
		menuRepository.save(menu);
		
		JSONObject json = new JSONObject();
		json.put("success", true);
		
		return json.toString();
	}
	
	@RequestMapping(value = "/secured/editSize"
			, method = RequestMethod.POST
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String editSize(long id, String title) throws JSONException {				
		Size size = sizeRepository.getOne(id);
		size.setTitle(title);
		sizeRepository.save(size);
		
		JSONObject json = new JSONObject();
		json.put("success", true);
		
		return json.toString();
	}
	
	@RequestMapping(value = "/secured/orderMenu"
			, method = RequestMethod.POST, produces={"text/plain;charset=UTF-8"})
    public @ResponseBody String orderMenu(String param) throws Exception {
		JSONObject jsonParam = new JSONObject(param);
		JSONObject json = new JSONObject();
		JSONArray menuList = jsonParam.getJSONArray("menuList");
		
		for(int i = 0 ; i < menuList.length() ; i++){
				JSONObject jsonMenu = menuList.getJSONObject(i);
				Menu menu = menuRepository.getOne(jsonMenu.getLong("id"));
				menu.setOrder(jsonMenu.getInt("order"));
				menuRepository.save(menu);
		}
		
		json = new JSONObject();
		json.put("success", true);		
	    return json.toString();
	}
}