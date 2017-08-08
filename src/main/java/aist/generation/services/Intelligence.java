package aist.generation.services;

import aist.generation.models.Page;
import aist.generation.models.PageType;
import org.springframework.stereotype.Service;

@Service
public interface Intelligence {
    PageType classify(Page data);
}
