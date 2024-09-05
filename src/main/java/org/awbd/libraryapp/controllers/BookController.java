package org.awbd.libraryapp.controllers;

import lombok.RequiredArgsConstructor;
import org.awbd.libraryapp.dtos.BookDto;
import org.awbd.libraryapp.models.Book;
import org.awbd.libraryapp.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final ModelMapper modelMapper;

    @RequestMapping("")
    public String getBooksPage(Model model,
                               @PageableDefault Pageable pageable,
                               @RequestParam(defaultValue = "number,asc") String sort) {
        if (pageable == null) {
            pageable = PageRequest.of(0, 5);
        }
        Page<BookDto> bookPage = bookService.findPage(pageable);
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("sortParam", sort);
        model.addAttribute("sortDir", sort.split(",")[1]);
        return "bookPaged";
    }

    @RequestMapping("/info/{id}")
    public String showById(@PathVariable String id, Model model) {
        Optional<Book> book = bookService.findById(id);
        BookDto dto = modelMapper.map(book.get(), BookDto.class);
        model.addAttribute("book", dto);
        return "bookInfo";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id, Model model) {
        Optional<Book> movieOpt = bookService.findById(id);
        if (movieOpt.isPresent()) {
            bookService.deleteById(id);
            return "redirect:/";
        } else {
            model.addAttribute("id", id);
            return "notFoundException";
        }
    }
}
