package com.poly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.beans.vocabularyEntity;

public interface vocabularyDAO extends JpaRepository<vocabularyEntity, Long> {
	@Query("SELECT MAX(v.index) FROM vocabularyEntity v")
	Long findMaxIndex();

	@Query("SELECT v FROM vocabularyEntity v WHERE v.name like %:name%")
	List<vocabularyEntity> findByName(String name);

	@Query("SELECT v FROM vocabularyEntity v WHERE v.mean like %:mean%")
	List<vocabularyEntity> findByMean(String mean);

	@Query("SELECT v FROM vocabularyEntity v WHERE v.type like %:type%")
	List<vocabularyEntity> findByType(String type);

	@Query("SELECT v FROM vocabularyEntity v WHERE " + "(v.name like %:name%) and " + "( v.mean like %:mean%)")
	List<vocabularyEntity> findByNameMean(String name, String mean);

	@Query("SELECT v FROM vocabularyEntity v WHERE " + "(v.mean like %:mean%) and " + "( v.type like %:type%)")
	List<vocabularyEntity> findByMeanType(String mean, String type);

	@Query("SELECT v FROM vocabularyEntity v WHERE " + "(v.name like %:name%) and " + "( v.type like %:type%)")
	List<vocabularyEntity> findByNameType(String name, String type);

	List<vocabularyEntity> findByNameContainingIgnoreCase(String name);

	@Query("SELECT v FROM vocabularyEntity v WHERE " + "(v.name like %:name%) and " + "( v.mean like %:mean%) and"+ "( v.type like %:type%)")
	List<vocabularyEntity> searchByNameMeanType(String name, String mean, String type);
}
