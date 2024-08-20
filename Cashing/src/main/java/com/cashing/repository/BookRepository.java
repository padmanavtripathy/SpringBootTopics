package com.cashing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cashing.dto.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	@Modifying
	@Transactional
	@Query("Update Book b set b.author_address=?2 where b.id=?1")
	public void updateAddress(int id,String address);
}
