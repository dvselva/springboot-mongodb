package com.selva.SpingMongo.service;

import com.selva.SpingMongo.exception.BooksCollectionException;
import com.selva.SpingMongo.model.Book;
import com.selva.SpingMongo.model.HscDraft;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface HscService {



    public List<HscDraft> getHscDraftByDraftId(Integer id);

//    public List<HscDraft> findAllWithPages(Pageable p);

    public Map<String, Object> getAllHscDraftInPages(int pageNo, int pageSize, String sortBy);
    public List<HscDraft> getAllHscDraft();

    public void createHsc(HscDraft hscdraft);

    public void deleteHsc (String id) throws Exception;


    List <HscDraft> getAllHscDraftByExample(HscDraft hsc);

    List<HscDraft> getAllHscDraftByNdbMpin(String ndbMpin);
}
