package com.selva.SpingMongo.controller;

import com.selva.SpingMongo.exception.BooksCollectionException;
import com.selva.SpingMongo.model.Book;
import com.selva.SpingMongo.model.HscDraft;
import com.selva.SpingMongo.service.BookService;
import com.selva.SpingMongo.service.HscService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class HscController {

    @Autowired
    private HscService hscservice;

    @GetMapping("/hscdrafts")
    public ResponseEntity<?> getAllHscDraft() {
        List<HscDraft> hscDrafts = hscservice.getAllHscDraft();
        return new ResponseEntity<>(hscDrafts, !hscDrafts.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);

    }



    @GetMapping("/hscdrafts/example")
    public List <HscDraft> getAllHscDraftByExample(@RequestBody HscDraft hsc) {
        return  hscservice.getAllHscDraftByExample(hsc);


    }


    @GetMapping("/hscdrafts/ndbmpin")
    public List <HscDraft> getAllHscDraftByNdbMpin(@RequestParam(name="ndbmpin") String ndbmpin)
    {
        return hscservice.getAllHscDraftByNdbMpin(ndbmpin);

    }

    @GetMapping("/hscdrafts/page")
    public Map<String ,Object> getAllHscDraftInPages(@RequestParam(name="pageno",defaultValue="0") int pageNo,
                                                     @RequestParam(name="pagesize",defaultValue = "2") int pageSize,
                                                     @RequestParam(name="sortby",defaultValue = "id") String sortBy) {
        return hscservice.getAllHscDraftInPages(pageNo,pageSize,sortBy);

    }


    @GetMapping("/hscdraftbyId/{id}")
    public ResponseEntity<?> getHscDraftByDraftId(@PathVariable("id") Integer id) {
        List<HscDraft> hscDrafts = hscservice.getHscDraftByDraftId(id);
        return new ResponseEntity<>(hscDrafts, !hscDrafts.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);

    }

//    @GetMapping("/hscdraftpages")
//    public ResponseEntity<?> getHscDraftWithPages() {
//
//        Pageable pageable = PageRequest.of(0,3);
//        List <HscDraft> hscDrafts = hscservice.findAllWithPages(pageable);
//        return new ResponseEntity<>(hscDrafts, !hscDrafts.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
//
//    }

    @PostMapping("/hscdraft")
    public ResponseEntity<?> createHscDraft(@RequestBody HscDraft hscdraft)  {
        try {
            hscservice.createHsc(hscdraft);
            return new ResponseEntity<HscDraft>(hscdraft,HttpStatus.OK);

        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("hscdrafts/{id}")
    public  ResponseEntity<?> deleteById(@PathVariable("id") String id) throws Exception {

        try {
            hscservice.deleteHsc(id);
            return new ResponseEntity<>("Successfully deleted the record " + id, HttpStatus.OK);
        }
        catch (BooksCollectionException e ) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


}
