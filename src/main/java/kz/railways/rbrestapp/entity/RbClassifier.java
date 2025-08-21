package kz.railways.rbrestapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="rb_classifiers")
public class RbClassifier {
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "title")
	private String title;
}
