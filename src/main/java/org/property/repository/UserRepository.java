package org.property.repository;

import org.property.beans.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sowmyaparameshwara on 3/29/17.
 */
public interface UserRepository extends CrudRepository<User,Integer> {

}
