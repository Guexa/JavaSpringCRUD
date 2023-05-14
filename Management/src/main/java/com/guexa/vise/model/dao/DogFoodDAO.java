
package com.guexa.vise.model.dao;

import com.guexa.vise.model.entity.DogFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guexa
 */

@Repository
public interface DogFoodDAO extends JpaRepository<DogFood, Long>
{
    
}
