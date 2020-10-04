package taxiApp.springapp.repos.impls;

import taxiApp.core.CV;
import org.springframework.data.repository.NoRepositoryBean;
import taxiApp.springapp.repos.CVRepository;

@NoRepositoryBean
public class CVRepositoryImpl extends CrudRepositoryImpl<CV> implements CVRepository {
}
