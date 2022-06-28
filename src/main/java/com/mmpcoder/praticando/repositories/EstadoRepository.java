/*CAMADA REPOSITORY - CATEGORIA --> ESSA CAMADA IRA ACESSAR O BANCO DE DADOS
 * ESSA CAMADA IRA REALIZAR O ACESSO A DADOS COMO DELETAR, ATUALIZAR ETC, PARA A CLASSE CATEGORIA.
 */

package com.mmpcoder.praticando.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmpcoder.praticando.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
