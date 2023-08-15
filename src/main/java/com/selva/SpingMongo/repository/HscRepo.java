package com.selva.SpingMongo.repository;

import com.selva.SpingMongo.model.HscDraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface HscRepo extends MongoRepository<HscDraft,String> {


    @Query("{'hscDraftId':?0}")
    List<HscDraft> findDraftById(Integer draftid);

    List<HscDraft>  findByNdbMpin(String mpin);
}
