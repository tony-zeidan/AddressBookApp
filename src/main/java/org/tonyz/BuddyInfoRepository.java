package org.tonyz;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    List<BuddyInfo> findByName(String name);

    List<BuddyInfo> findByPhoneNumber(String phoneNumber);

    BuddyInfo findById(long id);
}
