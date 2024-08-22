package com.poly.controllers;

import java.util.List;

import org.antlr.v4.runtime.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.beans.vocabularyEntity;
import com.poly.dao.vocabularyDAO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("mylib")
public class vacabularyCtrl {
	@Autowired
	vocabularyDAO vocaDao;

	@GetMapping("vocabulary/index")
	public String index(Model model) {
		model.addAttribute("action","add");
		vocabularyEntity word = new vocabularyEntity();
		setWord(null, word);
		model.addAttribute("word", word);
		List<vocabularyEntity> list_vocabulary = vocaDao.findAll();
		model.addAttribute("type_btn_edit", "collapse");
		model.addAttribute("type_btn_add", "show");
		model.addAttribute("list_vocabulary", list_vocabulary);
		return "vocabulary";
	}

	@PostMapping("vocabulary/add")
	public String save(Model model, @ModelAttribute("word") vocabularyEntity word) {
		model.addAttribute("action","add");
		String message = "Vui lòng nhập ô tên của từ và nghĩa của từ!";
		String typeMessage = "collapse";
		String type_alert = "alert-danger";
		Long maxIndex = vocaDao.findMaxIndex();
		if(maxIndex == null) {
			maxIndex = 0L;
		}
		word.setIndex(maxIndex + 1);
		word.setName(word.getName().trim());
		word.setMean(word.getMean().trim());
		word.setDescript(word.getDescript().trim());
		System.out.println(word);
		try {
			Boolean check = true;
			List<vocabularyEntity> list = vocaDao.findByName(word.getName().trim());
			for (vocabularyEntity v : list) {
				if (v.getMean().trim().equalsIgnoreCase(word.getMean().trim())) {
					check = false;
					System.out.println(check);
				}
			}
			if (check) {
				vocaDao.save(word);
				typeMessage = "show";
				type_alert = "alert-success";
				message = "Thêm thành công từ vựng mới " + "\"" + word.getName() + "\"" + "!";
				setWord(null, word);
			} else {
				typeMessage = "show";
				type_alert = "alert-warning";
				message = "Từ vựng " + "\"" + word.getName() + "\" đã tồn tại" + "!";
			}
		} catch (TransactionSystemException e) {
			typeMessage = "show";
		}
		model.addAttribute("type_btn_edit", "collapse");
		model.addAttribute("type_btn_add", "show");
		model.addAttribute("typeMessage", typeMessage);
		model.addAttribute("type_alert", type_alert);
		model.addAttribute("message", message);
		List<vocabularyEntity> list_vocabulary = vocaDao.findAll();
		model.addAttribute("list_vocabulary", list_vocabulary);
		return "vocabulary";
	}

	public void setWord(String value, vocabularyEntity word) {
		word.setIndex(null);
		word.setName(null);
		word.setType(null);
		word.setMean(null);
		word.setDescript(null);
	}

	@RequestMapping("/vocabulary/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		model.addAttribute("action","add");
		vocaDao.deleteById(id);
		vocabularyEntity word = new vocabularyEntity();
		setWord(null, word);
		model.addAttribute("word", word);
		model.addAttribute("type_btn_edit", "collapse");
		model.addAttribute("type_btn_add", "show");
		List<vocabularyEntity> list_vocabulary = vocaDao.findAll();
		model.addAttribute("list_vocabulary", list_vocabulary);
		return "vocabulary";
	}
	
	@RequestMapping("/vocabulary/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("action","edit");
		vocabularyEntity word = vocaDao.getOne(id);
		System.out.println(word+"từ");
		model.addAttribute("word", word);
		List<vocabularyEntity> list_vocabulary = vocaDao.findAll();
		model.addAttribute("type_btn_edit", "show");
		model.addAttribute("type_btn_add", "collapse");
		model.addAttribute("list_vocabulary", list_vocabulary);
		return "vocabulary";
	}
	
	@RequestMapping("/vocabulary/edit")
	public String todoEdit(Model model ,@ModelAttribute("word") vocabularyEntity word) {
		model.addAttribute("action","edit");
		vocaDao.save(word);		
		String message = "Cập nhật thành công!";
		String typeMessage = "show";
		String type_alert = "alert-success";
		model.addAttribute("typeMessage", typeMessage);
		model.addAttribute("type_alert", type_alert);
		model.addAttribute("message", message);
		List<vocabularyEntity> list_vocabulary = vocaDao.findAll();
		model.addAttribute("type_btn_edit", "show");
		model.addAttribute("type_btn_add", "collapse");
		model.addAttribute("list_vocabulary", list_vocabulary);
		return "vocabulary";
	}

}
