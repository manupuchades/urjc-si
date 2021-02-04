/**
 * 
 */
package es.urjc.si.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.models.CrewMember;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {

}
