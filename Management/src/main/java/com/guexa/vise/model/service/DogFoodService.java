
package com.guexa.vise.model.service;

import com.guexa.vise.model.entity.DogFood;
import java.util.List;

/**
 *
 * @author Guexa
 */
public interface DogFoodService 
{
    List<DogFood> getDogFood();
    
    DogFood findDogFoodById(Long id);
    DogFood saveDogFood(DogFood dogFood);
    
    void delete(Long id);
    
    List<DogFood> findByBrand(String brand);
    
}
