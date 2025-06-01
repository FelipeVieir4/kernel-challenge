package com.br.kchallenge.crud.repository;

import com.br.kchallenge.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 

@Repository // Indicates that this interface is a Spring Data repository
public interface IUserRepository extends JpaRepository<User, Long> {
    // This interface will inherit methods for CRUD operations on User entities
    // The first parameter is the entity type (User), and the second is the type of
    // the primary key (Long)

}

/*
 * PORQUE UMA INTERFACE UserRepository extends JpaRepository<User, Long> E NÃO
 * public class UserRepository implements JpaRepository<User, Long>?
 *
 * (public class UserRepository implements JpaRepository) faria todo o sentido
 * em um cenário Java tradicional onde você precisa fornecer a implementação
 * para os métodos de uma interface.
 * 
 * No entanto, o Spring Data JPA funciona de uma maneira um pouco "mágica" e
 * muito conveniente aqui.
 * 
 * Por que UserRepository é uma interface que extends JpaRepository:
 * 
 * Geração Automática de Implementação: A grande sacada do Spring Data JPA é que
 * você não precisa escrever a implementação para os métodos CRUD básicos (como
 * save(), findById(), findAll(), delete(), etc.).
 * 
 * Quando você declara uma interface como UserRepository e faz ela extends
 * JpaRepository<User, Long>, o Spring Data JPA, em tempo de execução,
 * automaticamente cria uma classe "proxy" que implementa essa interface
 * UserRepository (e, por consequência, todos os métodos herdados de
 * JpaRepository).
 * Essa classe gerada pelo Spring contém todo o código necessário para interagir
 * com o banco de dados usando o JPA (Hibernate, no nosso caso).
 * Definindo o Contrato: Sua interface UserRepository serve como um contrato.
 * Você está dizendo ao Spring:
 * "Eu preciso de um componente que saiba como realizar operações de
 * persistência para a minha entidade User,
 * e o ID é do tipo Long. Além disso, ele deve ter todos os métodos padrão do
 * JpaRepository e, futuramente,
 * talvez alguns métodos customizados que eu declararei aqui (como
 * findByEmail(String email))."
 * 
 * Interface extends Interface: Em Java, é perfeitamente normal e correto uma
 * interface estender outra interface. Isso significa que UserRepository herda
 * todas as declarações de métodos de JpaRepository.
 * 
 * Se UserRepository fosse public class UserRepository implements JpaRepository:
 * 
 * Nesse caso, você seria o responsável por escrever o código dentro da classe
 * UserRepository para cada um dos métodos declarados em JpaRepository. Por
 * exemplo, você teria que escrever o código para o método save(User user), o
 * método findById(Long id), e assim por diante, usando o EntityManager do JPA
 * para fazer as operações no banco.
 * Isso anularia um dos maiores benefícios do Spring Data JPA, que é justamente
 * te poupar de escrever todo esse código repetitivo de acesso a dados.
 * Em Resumo:
 * 
 * Com o Spring Data JPA, você define a interface (o "o quê"), e o Spring cuida
 * da implementação (o "como") para os métodos mais comuns. Isso permite que
 * você se concentre na lógica de negócio e em consultas mais específicas,
 * quando necessário, que você pode declarar na sua interface UserRepository (e
 * o Spring também tentará implementá-las automaticamente se você seguir as
 * convenções de nomenclatura).
 */