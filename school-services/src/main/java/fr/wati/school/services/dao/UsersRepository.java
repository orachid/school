/**
 * 
 */
package fr.wati.school.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wati.school.entities.bean.Users;

/**
 * @author Rachid Ouattara
 *
 */
public interface UsersRepository extends JpaRepository<Users, Long>{

}
