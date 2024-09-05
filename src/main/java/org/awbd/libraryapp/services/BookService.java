package org.awbd.libraryapp.services;

import lombok.RequiredArgsConstructor;
import org.awbd.libraryapp.dtos.BookDto;
import org.awbd.libraryapp.models.Book;
import org.awbd.libraryapp.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public Page<BookDto> findPage(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(book -> modelMapper.map(book, BookDto.class));
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}
