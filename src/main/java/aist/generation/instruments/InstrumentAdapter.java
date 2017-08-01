package aist.generation.instruments;

import aist.generation.models.Page;

/**
 * Created by justinp on 7/26/17.
 */
public interface InstrumentAdapter {
    Page get(String url);
}
