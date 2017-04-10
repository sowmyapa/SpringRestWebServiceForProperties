package org.property.controllers;

import org.property.beans.*;
import org.property.constants.PaginationConstants;
import org.property.repository.PropertyRepository;
import org.property.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * server.port: 8443
 server.ssl.key-store: keystore.p12
 server.ssl.key-store-password: password
 server.ssl.keyStoreType: PKCS12
 server.ssl.keyAlias: tomcat


 * Created by sowmyaparameshwara on 3/19/17.
 */
@CrossOrigin
@Controller
@RequestMapping(path="/properties")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

//( produces = MediaType.APPLICATION_JSON_VALUE)
/*    @GetMapping("/list")
    public @ResponseBody PropertyResponse getPropertyList(@RequestParam(value="perPage",defaultValue= PaginationConstants.DEFAULT_PAGE_SIZE,required = false) String perPage,
                                  @RequestParam(value="offset", defaultValue=PaginationConstants.DEFAULT_PAGE_OFFSET)String offset,
                                  @RequestParam(value="propertyName")String propertyName,*//*
                                                          @RequestParam Map<String,String> allRequestParams,*//*
                                                          @RequestParam(value="propertyPrice")String propertyPrice,
                                  Model model){
        PropertyResponse propertyResponse = new PropertyResponse();
        int size = Integer.parseInt(perPage);
        int page = Integer.parseInt(offset)/size;
        List<Property> properties =  propertyRepository.findByPropertyNameAndPropertyPrice(propertyName,Float.parseFloat(propertyPrice),new PageRequest(page,size));
        propertyResponse.setRecords(properties);
        propertyResponse.setQueryRecordCount(100);
        propertyResponse.setTotalRecordCount(100);
        //propertyResponse.setPagination(new Pagination(50,Integer.parseInt(pageOffset),Integer.parseInt(pageSize)));
        return propertyResponse;
    }*/

    /**
     * MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
     headers.add("Content-Type", "application/json");

     restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
     HttpEntity<User> request = new HttpEntity<User>(user, headers);

     User user1 = restTemplate.postForObject("http://localhost:8080/user", request, User.class);
     return user1;
     */

    @GetMapping
    public @ResponseBody DynatablePropertyResponse getPropertyList(@RequestParam(value="perPage",defaultValue= PaginationConstants.DEFAULT_PAGE_SIZE,required = false) String perPage,
                                                          @RequestParam(value="offset", defaultValue=PaginationConstants.DEFAULT_PAGE_OFFSET)String offset,
                                                          @RequestParam(value="propertyName",required = false)String propertyName,
                                                          @RequestParam(value="queries[search]",required = false)String query,
                                                          @RequestParam(value="sorts[propertyPrice]",required = false)String sortPropertyPrice,
                                                          @RequestParam(value="sorts[propertySize]",required = false)String sortPropertySize,
                                                          @RequestParam Map<String,String> allRequestParams,
                                                          @RequestParam(value="propertyPrice",required = false)String propertyPrice,
                                                           @RequestParam(value="propertySize",required = false)String propertySize,
                                                           @RequestParam(value="propertyType",required = false)String propertyType,
                                                           @RequestParam(value="propertyArea",required = false)String propertyArea,
                                                          Model model){
        DynatablePropertyResponse propertyResponse = new DynatablePropertyResponse();
        if(query==null || query.isEmpty()){
            if(sortPropertyPrice!=null) {
                Sort.Direction d = sortPropertyPrice.equals("1")?Sort.Direction.DESC:Sort.Direction.ASC;
                paginationWithSort(propertyResponse, perPage, offset, propertyName, propertyPrice, propertySize, propertyType, propertyArea,d,"propertyPrice");
            }else if(sortPropertySize!=null){
                Sort.Direction d = sortPropertySize.equals("1")?Sort.Direction.DESC:Sort.Direction.ASC;
                paginationWithSort(propertyResponse, perPage, offset, propertyName, propertyPrice, propertySize, propertyType, propertyArea,d,"propertySize");
            }else{
                pagination(propertyResponse, perPage, offset, propertyName, propertyPrice, propertySize, propertyType, propertyArea);

            }
        }else{
            if(sortPropertyPrice!=null) {
                Sort.Direction d = sortPropertyPrice.equals("1")?Sort.Direction.DESC:Sort.Direction.ASC;
                paginationWithSearch(propertyResponse, perPage, offset, propertyName, propertyPrice, propertySize, propertyType, propertyArea,query,d,"propertyPrice");
            }else if(sortPropertySize!=null){
                Sort.Direction d = sortPropertySize.equals("1")?Sort.Direction.DESC:Sort.Direction.ASC;
                paginationWithSearch(propertyResponse, perPage, offset, propertyName, propertyPrice, propertySize, propertyType, propertyArea,query,d,"propertySize");
            }else{
                paginationWithSearch(propertyResponse,perPage,offset,propertyName,propertyPrice,propertySize,propertyType,propertyArea,query,null,null);

            }

        }

        return propertyResponse;
    }

    private void paginationWithSearch(DynatablePropertyResponse propertyResponse, String perPage, String offset, String propertyName, String propertyPrice, String propertySize, String propertyType, String propertyArea, String query,Sort.Direction direction, String sortAttribute) {
        int size = Integer.parseInt(perPage);
        int page = Integer.parseInt(offset)/size;
        Map<String,String> filters = new HashMap<String,String>();
        filters.put("propertyName",propertyName);
        filters.put("propertyPrice",propertyPrice);
        filters.put("propertySize",propertySize);
        filters.put("propertyType",propertyType);
        filters.put("propertyArea",propertyArea);
        Page<Property> properties;
        if(direction!=null){
            properties = (Page<Property>) propertyRepository.findAll(getFilterSpecification(filters), new PageRequest(page, size,direction,sortAttribute));
        }else {
            properties = (Page<Property>) propertyRepository.findAll(getFilterSpecification(filters), new PageRequest(page, size));
        }
        List<DynatablePropertyRecords> dynatablePropertyRecords = new ArrayList<>();
        int count=0;
        for(Property property : properties.getContent()){
            if(property.toString().toLowerCase().contains(query.toLowerCase())) {
                count++;
                dynatablePropertyRecords.add(new DynatablePropertyRecords(property));
            }
        }
        propertyResponse.setRecords(dynatablePropertyRecords);
        propertyResponse.setQueryRecordCount(count);
        propertyResponse.setTotalRecordCount(count);
    }

    private void pagination(DynatablePropertyResponse propertyResponse, String perPage, String offset, String propertyName, String propertyPrice, String propertySize, String propertyType, String propertyArea) {
        int size = Integer.parseInt(perPage);
        int page = Integer.parseInt(offset)/size;
        Map<String,String> filters = new HashMap<String,String>();
        filters.put("propertyName",propertyName);
        filters.put("propertyPrice",propertyPrice);
        filters.put("propertySize",propertySize);
        filters.put("propertyType",propertyType);
        filters.put("propertyArea",propertyArea);

        Page<Property> properties = (Page<Property>) propertyRepository.findAll(getFilterSpecification(filters),new PageRequest(page,size));
        List<DynatablePropertyRecords> dynatablePropertyRecords = new ArrayList<>();
        for(Property property : properties.getContent()){
            dynatablePropertyRecords.add(new DynatablePropertyRecords(property));
        }
        propertyResponse.setRecords(dynatablePropertyRecords);
        propertyResponse.setQueryRecordCount((int) properties.getTotalElements());
        propertyResponse.setTotalRecordCount((int) properties.getTotalElements());
    }

    private void paginationWithSort(DynatablePropertyResponse propertyResponse, String perPage, String offset, String propertyName, String propertyPrice, String propertySize, String propertyType, String propertyArea,Sort.Direction d,String sortAttribute) {
        int size = Integer.parseInt(perPage);
        int page = Integer.parseInt(offset)/size;
        Map<String,String> filters = new HashMap<String,String>();
        filters.put("propertyName",propertyName);
        filters.put("propertyPrice",propertyPrice);
        filters.put("propertySize",propertySize);
        filters.put("propertyType",propertyType);
        filters.put("propertyArea",propertyArea);

        Page<Property> properties = (Page<Property>) propertyRepository.findAll(getFilterSpecification(filters),new PageRequest(page,size,d,sortAttribute));
        List<DynatablePropertyRecords> dynatablePropertyRecords = new ArrayList<>();
        for(Property property : properties.getContent()){
            dynatablePropertyRecords.add(new DynatablePropertyRecords(property));
        }
        propertyResponse.setRecords(dynatablePropertyRecords);
        propertyResponse.setQueryRecordCount((int) properties.getTotalElements());
        propertyResponse.setTotalRecordCount((int) properties.getTotalElements());
    }


    @GetMapping(value="/user/{userId}")
    public @ResponseBody User getUserData( @PathVariable int userId){
        return userRepository.findOne(userId);
    }

    @PostMapping
    public @ResponseBody String addNewProperty(@RequestBody List<Property> properties){
        for(Property prop : properties) {
            propertyRepository.save(prop);
        }
          return "Saved";
    }

    @PutMapping
    public @ResponseBody String updateNewProperty(@RequestBody List<Property> properties){
        for(Property prop : properties) {
            propertyRepository.save(prop);
        }
        return "Updated";
    }

    @DeleteMapping
    public @ResponseBody String deleteProperty(@RequestParam String propertyId){
        propertyRepository.delete(Integer.parseInt(propertyId));
        return "Deleted";
    }


    private Specification<Property> getFilterSpecification(
            Map<String, String> filterValues) {

        return (Root<Property> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Optional<Predicate> predicate = filterValues.entrySet().stream()
                    .filter(v -> v.getValue() != null && v.getValue().length() > 0)
                    .map(entry -> {
                        Path<?> path = root;
                        String key = entry.getKey();
                        if (entry.getKey().contains(".")) {
                            String[] splitKey = entry.getKey().split("\\.");
                            path = root.join(splitKey[0]);
                            key = splitKey[1];
                        }
                        System.out.println(builder.toString());
                        return builder.like(path.get(key).as(String.class),
                                "%" + entry.getValue() + "%");
                    })
                    .collect(Collectors.reducing((a, b) -> builder.and(a, b)));
            return predicate.orElseGet(() -> alwaysTrue(builder));
        };
    }

    private Predicate alwaysTrue(CriteriaBuilder builder) {
        return builder.isTrue(builder.literal(true));
    }

}
