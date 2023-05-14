
package com.guexa.vise.controller;

import com.guexa.vise.model.entity.DogFood;
import com.guexa.vise.model.service.DogFoodService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Guexa
 */

@RestController
@RequestMapping("/api")
public class DogFoodController 
{
    @Autowired
    private DogFoodService dogFoodService;
    
    // Internationalitation server
    @GetMapping("/international")
    public String getInternationalPage()
    {
        return "international";
    }
    
    @GetMapping("/dogfood")
    public List<DogFood> index()
    {
        return dogFoodService.getDogFood();
    }
    
    // Show dog food list
    @GetMapping("/dogfood/{id}")
    public ResponseEntity<?> show(@PathVariable Long id)
    {
        DogFood dogFood;
        Map<String, Object> response = new HashMap<>();
        
        try
        {
            dogFood = dogFoodService.findDogFoodById(id);
        }
        catch(DataAccessException e)
        {
            response.put("messaje", "Acces Denied to Database");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        if(dogFood == null)
        {
            response.put("messaje", "Name not found in the database");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(dogFood, HttpStatus.OK);
    }
    
    // Save new querie
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody DogFood dogFood)
    {
        DogFood newDogFood;
        Map<String, Object> response = new HashMap<>();
        
        try
        {
            newDogFood = dogFoodService.saveDogFood(dogFood);
        }
        catch(DataAccessException e)
        {
            response.put("messaje", "Fail while tried to insert a querie into the database");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("Message", "Dog Food inserted Succesfully");
        response.put("Dog Food", newDogFood);
        
        return new ResponseEntity<>(dogFood, HttpStatus.CREATED);
    }
    
    // Edit some querie
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody DogFood dogFood, @PathVariable Long id)
    {
        DogFood df = dogFoodService.findDogFoodById(id);
        DogFood dfUpdated;
        
        Map<String, Object> response = new HashMap<>();
        
        if(df == null)
        {
            response.put("message", "Filter don't successed because don't exist or the database is broken");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        try
        {
            df.setBrand(dogFood.getBrand());
            df.setBreeds(dogFood.getBreeds());
            df.setFoodName(dogFood.getFoodName());
            df.setPrice(dogFood.getPrice());
            df.setWeightKG(dogFood.getWeightKG());
            
            dfUpdated = dogFoodService.saveDogFood(df);
        }
        catch(DataAccessException e)
        {
            response.put("messaje", "Fail while tried to UPDATE the food into the database");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "dogFood updated succesfully");
        response.put("dogFoodUpdated", dfUpdated);
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    // Delete some querie
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        Map<String, Object> response = new HashMap<>();
        
        try
        {
            dogFoodService.delete(id);
        }
        catch(DataAccessException e)
        {
            response.put("messaje", "Fatal error, dog food couldn't be eliminated");
            response.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "Dog Food deleted succesfully");
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    // Filter Query by brand
    @GetMapping("/dogfood/brand")
    public ResponseEntity<List<DogFood>> getDogFoodByBrand(@PathVariable String brand)
    {
        return new ResponseEntity<>(dogFoodService.findByBrand(brand), HttpStatus.OK);
    }
}