package com.pi.feiradigital.repository;

import com.pi.feiradigital.model.Produto;
import com.pi.feiradigital.model.type.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findByStatusAndCodigo(Status status, String codigo);

    @Query(value = "SELECT p.* FROM produto p  " +
            " INNER JOIN vendedor v ON p.vendedor = v.id  " +
            " WHERE p.status = 'ATIVO' " +
            " AND CASE WHEN :precoMin AND :precoMax IS NOT NULL THEN preco BETWEEN :precoMin AND :precoMax ELSE TRUE END " +
            " AND CASE WHEN :categoria IS NOT NULL THEN categoria = :categoria ELSE TRUE END " +
            " AND CASE WHEN :vendedor IS NOT NULL THEN v.nome LIKE %:vendedor% ELSE TRUE END " +
            " AND CASE WHEN :nome IS NOT NULL THEN p.nome LIKE %:nome% ELSE TRUE END " +
            " ORDER BY preco DESC LIMIT :inicio, :total", nativeQuery = true)
    List<Produto> filtarProdutosPrecoDesc(@Param("precoMin") Double precoMin,
                                          @Param("precoMax") Double precoMax,
                                          @Param("categoria") String categoria,
                                          @Param("vendedor") String vendedor,
                                          @Param("nome") String nome,
                                          @Param("inicio") Integer inicio,
                                          @Param("total") Integer total);

    @Query(value = "SELECT p.* FROM produto p  " +
            " INNER JOIN vendedor v ON p.vendedor = v.id  " +
            " WHERE p.status = 'ATIVO' " +
            " AND CASE WHEN :precoMin AND :precoMax IS NOT NULL THEN preco BETWEEN :precoMin AND :precoMax ELSE TRUE END " +
            " AND CASE WHEN :categoria IS NOT NULL THEN categoria = :categoria ELSE TRUE END " +
            " AND CASE WHEN :vendedor IS NOT NULL THEN v.nome LIKE %:vendedor% ELSE TRUE END " +
            " AND CASE WHEN :nome IS NOT NULL THEN p.nome LIKE %:nome% ELSE TRUE END " +
            " ORDER BY preco ASC LIMIT :inicio, :total", nativeQuery = true)
    List<Produto> filtarProdutosPrecoAsc(@Param("precoMin") Double precoMin,
                                         @Param("precoMax") Double precoMax,
                                         @Param("categoria") String categoria,
                                         @Param("vendedor") String vendedor,
                                         @Param("nome") String nome,
                                         @Param("inicio") Integer inicio,
                                         @Param("total") Integer total);

    @Query(value = "SELECT COUNT(*) FROM produto p  " +
            " INNER JOIN vendedor v ON p.vendedor = v.id  " +
            " WHERE p.status = 'ATIVO' " +
            " AND CASE WHEN :precoMin AND :precoMax IS NOT NULL THEN preco BETWEEN :precoMin AND :precoMax ELSE TRUE END " +
            " AND CASE WHEN :categoria IS NOT NULL THEN categoria = :categoria ELSE TRUE END " +
            " AND CASE WHEN :vendedor IS NOT NULL THEN v.nome LIKE %:vendedor% ELSE TRUE END " +
            " AND CASE WHEN :nome IS NOT NULL THEN p.nome LIKE %:nome% ELSE TRUE END ", nativeQuery = true)
    Long totalFiltarProdutosPreco(@Param("precoMin") Double precoMin,
                                     @Param("precoMax") Double precoMax,
                                     @Param("categoria") String categoria,
                                     @Param("vendedor") String vendedor,
                                     @Param("nome") String nome);
}
