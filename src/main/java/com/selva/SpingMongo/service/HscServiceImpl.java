package com.selva.SpingMongo.service;

import com.selva.SpingMongo.model.HscDraft;
import com.selva.SpingMongo.repository.HscRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HscServiceImpl implements HscService{

    @Autowired
    private HscRepo hscrepo;


    public Map<String ,Object> getAllHscDraftInPages (int pageNo, int pageSize, String sortBy)
    {

        Map <String , Object> response = new HashMap<String,Object>();
        Sort sort = Sort.by(sortBy);
        Pageable page = PageRequest.of(pageNo,pageSize,sort);

        Page <HscDraft> hscDraftPage = hscrepo.findAll(page);
        response.put("data",hscDraftPage.getContent());
        response.put("Total No of page",hscDraftPage.getTotalPages());
        response.put("Total No of Elements",hscDraftPage.getTotalElements());
        response.put("Current Page No",hscDraftPage.getNumber());

        return response;
    }

    @Override
    public List<HscDraft> getAllHscDraftByExample(HscDraft hsc) {

        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        //ExampleMatcher matcher2 = ExampleMatcher.matchingAny().withMatcher((""), ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING));
        Example<HscDraft> ex = Example.of(hsc,matcher);
        return hscrepo.findAll(ex);
    }

    //    public List<HscDraft> findAllWithPages(Pageable p) {
//
//       List<HscDraft> hscDrafts =  hscrepo.findAll(p).getContent();
//
//
//        if (!hscDrafts.isEmpty()) {
//            return hscDrafts;
//        }
//        else
//       {
//           return new ArrayList<HscDraft>();
//       }
//    }

    public List<HscDraft> getHscDraftByDraftId(Integer id) {

        List<HscDraft> hscDrafts =  hscrepo.findDraftById(id);

        if (!hscDrafts.isEmpty()) {
            return hscDrafts;
        }
        else
        {
            return new ArrayList<HscDraft>();
        }
    }




    @Override
    public List<HscDraft> getAllHscDraftByNdbMpin(String ndbMpin) {
        List<HscDraft> hscDrafts =  hscrepo.findByNdbMpin(ndbMpin);

        if (!hscDrafts.isEmpty()) {
            return hscDrafts;
        }
        else
        {
            return new ArrayList<HscDraft>();
        }
    }

    public List<HscDraft> getAllHscDraft() {
        List<HscDraft> hscDrafts =  hscrepo.findAll();


        if (!hscDrafts .isEmpty()) {
            return hscDrafts;
        }
        else
        {
            return new ArrayList<HscDraft>();
        }
    }

    public void createHsc(HscDraft hscdraft) {
        hscrepo.save(hscdraft);
    }


    public void deleteHsc (String id) throws Exception {

        Optional<HscDraft> tobeDeleted = hscrepo.findById(id);


        if (tobeDeleted.isEmpty()) {
           throw new Exception("Record not found");
        }
        else
        {
            hscrepo.deleteById(id);

        }


    }


}
