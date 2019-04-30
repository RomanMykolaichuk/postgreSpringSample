/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.e4x.postgrespringsample;

/**
 *
 * @author User
 */

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableOneRepository extends CrudRepository<TableOne, Integer> {
List<TableOne> findByName(String name);
}

