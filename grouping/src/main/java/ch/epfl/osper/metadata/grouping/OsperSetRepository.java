package ch.epfl.osper.metadata.grouping;

import org.springframework.data.repository.CrudRepository;

import javax.inject.Named;
import java.math.BigInteger;

/**
 * Created by kryvych on 25/09/15.
 */
@Named
public interface OsperSetRepository extends CrudRepository<OsperSet, BigInteger> {

    OsperSet findByName(String name);

    OsperSet findBySpec(String spec);

    OsperSet findByDisplayName(String displayName);

    Iterable<OsperSet> findBySensors(String sensor);


}
