package org.property.repository;

import org.property.beans.Property;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sowmyaparameshwara on 3/19/17.
 */
public interface PropertyRepository extends PagingAndSortingRepository<Property,Integer>,JpaSpecificationExecutor<Property> {

    List<Property> findByPropertyName(String propertyName, Pageable pageable);


    List<Property> findByPropertyNameAndPropertyPrice(String propertyName,float propertyPrice, Pageable pageable);


    List<Property> findByPropertySize(int propertySize, Pageable pageable);

    @Query("select p from Property p where p.propertyArea = ?")
    List<Property> queryByPropertyArea(String propertyArea);




    //List<Property> findAll(Specification<Property> spec);


   // List<Property> findWithExample(Property property,Pageable pageable);



}
