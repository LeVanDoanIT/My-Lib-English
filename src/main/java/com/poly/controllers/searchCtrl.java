package com.poly.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.beans.vocabularyEntity;
import com.poly.dao.vocabularyDAO;

@Controller
public class searchCtrl {
	@Autowired
	vocabularyDAO dao;

	@RequestMapping("/mylib/vocabulary/search/{name}{mean}{type}")
	public String search(Model model, vocabularyEntity word, @RequestParam("name") String name,
			@RequestParam("mean") String mean, @RequestParam("type") String type) {
		List<vocabularyEntity> list_vocabulary = dao.findAll();
		word.setIndex(null);
		word.setName(null);
		word.setType(null);
		word.setMean(null);
		word.setDescript(null);
		if (!name.isEmpty() && mean.isEmpty() && type.equalsIgnoreCase("all")) {
			list_vocabulary = dao.findByName(name);
		} else if (name.isEmpty() && !mean.isEmpty() && type.equalsIgnoreCase("all")) {
			list_vocabulary = dao.findByMean(mean);
		} else if (name.isEmpty() && mean.isEmpty() && !type.equalsIgnoreCase("all")) {
			list_vocabulary = dao.findByType(type);
		} else if (!name.isEmpty() && !mean.isEmpty() && type.equalsIgnoreCase("all")) {
			list_vocabulary = dao.findByNameMean(name, mean);
		} else if (name.isEmpty() && !mean.isEmpty() && !type.equalsIgnoreCase("all")) {
			list_vocabulary = dao.findByMeanType(mean, type);
		} else if (!name.isEmpty() && mean.isEmpty() && !type.equalsIgnoreCase("all")) {
			list_vocabulary = dao.findByNameType(name, type);
		} else if (!name.isEmpty() && !mean.isEmpty() && !type.equalsIgnoreCase("all")) {
			list_vocabulary = dao.searchByNameMeanType(name, mean, type);
		} else {
			model.addAttribute("typeMessage", "show");
			model.addAttribute("type_alert", "alert-danger");
			model.addAttribute("message", "Vui lòng nhập dữ liệu để tìm kiếm");
		}
		if(list_vocabulary.isEmpty()) {
			model.addAttribute("typeMessage", "show");
			model.addAttribute("type_alert", "alert-warning");
			model.addAttribute("message", "Không tìm thấy kết quả");
		}
		model.addAttribute("type_btn_edit", "collapse");
		model.addAttribute("type_btn_add", "show");
		model.addAttribute("word", word);
		model.addAttribute("list_vocabulary", list_vocabulary);
		return "vocabulary";
	}

}
