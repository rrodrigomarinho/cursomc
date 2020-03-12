package com.rodrigomarinho.cursomc.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rodrigomarinho.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	@Transactional
	@Query("select obj from Cidade obj where obj.estado.id = :estadoId order by obj.nome")
	public List<Cidade> findCidades(@Param("estadoId") Integer estadoId);
}
