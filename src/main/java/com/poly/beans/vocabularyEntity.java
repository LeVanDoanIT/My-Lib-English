package com.poly.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "vocabulary")
public class vocabularyEntity {
	@Id
	@Column(name = "word_index")
	private Long index;
	@Column
	@NotNull
	@NotEmpty
	private String name;
	@Column
	private String type;
	@Column
	@NotNull
	@NotEmpty
	private String mean;
	@Column
	private String descript;
}
