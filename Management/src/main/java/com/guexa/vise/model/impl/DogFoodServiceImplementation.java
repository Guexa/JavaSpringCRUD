
package com.guexa.vise.model.impl;

import com.guexa.vise.model.dao.DogFoodDAO;
import com.guexa.vise.model.entity.DogFood;
import com.guexa.vise.model.service.DogFoodService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guexa
 */

@Service
public class DogFoodServiceImplementation implements DogFoodService
{
    @Autowired
    private DogFoodDAO dogFoodDAO;
    
    @Override
    public List<DogFood> getDogFood()
    {
        return dogFoodDAO.findAll();
    }

    @Override
    public DogFood findDogFoodById(Long id) 
    {
        return dogFoodDAO.findById(id).orElse(null);
    }

    @Override
    public DogFood saveDogFood(DogFood dogFood) 
    {
        return dogFoodDAO.save(dogFood);
    }

    @Override
    public void delete(Long id) 
    {
        dogFoodDAO.deleteById(id);
    }

    @Override
    public List<DogFood> findByBrand(String brand) 
    {
        return dogFoodDAO.findAll(Sort.by("brand").ascending());
    }
}
