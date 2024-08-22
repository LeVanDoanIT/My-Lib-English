package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.beans.vocabularyEntity;

public interface vocabularyDAO extends JpaRepository<vocabularyEntity, Long>{
	@Query("SELECT MAX(v.index) FROM vocabularyEntity v")
    Long findMaxIndex();
	@Query (name="findByName")
	List<vocabularyEntity> findByName(String name);
	@Query (name="findByMean")
	List<vocabularyEntity> findByMean(String Mean);
	
	@Query("UPDATE vocabularyEntity e SET e.name = :name, e.type = :type, e.mean = :mean, e.descript = :descript WHERE e.index = :id")
     Integer updateWordById(Long id, String name, String type, String mean, String descript);
	
	 
}
