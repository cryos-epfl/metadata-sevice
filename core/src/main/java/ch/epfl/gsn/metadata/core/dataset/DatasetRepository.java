package ch.epfl.gsn.metadata.core.dataset;

import ch.epfl.gsn.metadata.core.dataset.model.OsperDataset;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Named;
import java.math.BigInteger;

/**
 * Created by kryvych on 07/03/16.
 */
@Named
public interface DatasetRepository extends CrudRepository<OsperDataset, BigInteger>{

}