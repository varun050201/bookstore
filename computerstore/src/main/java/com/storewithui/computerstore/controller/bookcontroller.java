package com.storewithui.computerstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.storewithui.computerstore.entity.book;
import com.storewithui.computerstore.entity.mybooklist;
import com.storewithui.computerstore.service.bookservice;
import com.storewithui.computerstore.service.mybooklistservice;

@Controller
public class bookcontroller {

    @Autowired
    private bookservice service;

    @Autowired
    private mybooklistservice mybookservice;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookregister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllBooks() {
        List<book> list = service.getallbook();
        return new ModelAndView("booklist", "book", list); // 'booklist.html' template expected in your templates folder
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute book b) {
        service.save(b);
        return "redirect:/available_books";
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model) {
        List<mybooklist> list = mybookservice.getAllMyBooks();
        model.addAttribute("book", list);
        return "myBooks";
    }

//    @RequestMapping("/mylist/{id}")
//    public String getMyList(@PathVariable("id") int id) {
//        book b = service.getbookbyid(id);
//        mybooklist mb = new mybooklist(b.getId(), b.getName(), b.getAuthor(), b.getPrice());
//        mybookservice.savemybook(mb);
//        return "redirect:/my_books";
//    }
    @RequestMapping("/mylist/{id}")
    public String addToMyList(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        book b = service.getbookbyid(id);
        mybooklist mb = new mybooklist(b.getId(), b.getName(), b.getAuthor(), b.getPrice());
        mybookservice.savemybook(mb);
        redirectAttributes.addFlashAttribute("successId", id);  // Send the ID of the added book
        return "redirect:/available_books";
    }



    @RequestMapping("/editbook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        book b = service.getbookbyid(id);
        model.addAttribute("book", b);
        return "editbook"; // Corresponds to editbook.html
    }

    // New method to handle form submission for updating the book
    @PostMapping("/updatebook")
    public String updateBook(@ModelAttribute book b) {
        // Save the updated book object
        service.save(b);
        return "redirect:/available_books";
    }

    @RequestMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        service.deletebyid(id);
        return "redirect:/available_books";
    }
}
