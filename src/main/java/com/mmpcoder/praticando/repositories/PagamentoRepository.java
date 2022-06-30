/*CAMADA REPOSITORY - CATEGORIA --> ESSA CAMADA IRA ACESSAR O BANCO DE DADOS
 * ESSA CAMADA IRA REALIZAR O ACESSO A DADOS COMO DELETAR, ATUALIZAR ETC, PARA A CLASSE CATEGORIA.
 */
// NESSE CASO FOI CRIADO APENAS O REPOSITY DA SUPERCLASSE
package com.mmpcoder.praticando.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmpcoder.praticando.domain.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
