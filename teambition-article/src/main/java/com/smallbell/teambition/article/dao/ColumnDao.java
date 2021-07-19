package com.smallbell.teambition.article.dao;

import com.smallbell.teambition.article.pojo.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ColumnDao extends JpaRepository<Column,String>,JpaSpecificationExecutor<Column>{
	
}
