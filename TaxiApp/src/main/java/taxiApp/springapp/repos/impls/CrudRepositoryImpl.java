package taxiApp.springapp.repos.impls;

import taxiApp.core.TaxiItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@NoRepositoryBean
public class CrudRepositoryImpl<T extends TaxiItem> implements CrudRepository<T, Long> {

    private Long idCounter = 0L;
    protected List<T> items = new ArrayList<T>();

    @Override
    public <S extends T> S save(S s) {
        if (s.getId() == null) {
            items.add(s);
            s.setId(idCounter++);
        }
        return s;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        for(T item: iterable) {
            save(item);
        }
        return iterable;
    }

    @Override
    public Optional<T> findById(Long id) {
        return items.stream()
                .filter(item -> Objects.equals(item.getId(), id))
                .findFirst();
    }

    @Override
    public boolean existsById(Long id) {
        return items.stream()
                .anyMatch(item -> Objects.equals(item.getId(), id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<T>(items);
    }

    @Override
    public Iterable<T> findAllById(Iterable<Long> iterable) {
        ArrayList<T> result =  new ArrayList<T>();
        for(Long id: iterable) {
            findById(id).ifPresent(result::add);
        }
        return result;
    }

    @Override
    public long count() {
        return items.size();
    }

    @Override
    public void deleteById(Long id) {
        items = items.stream()
                .filter(item -> !Objects.equals(item.getId(), id))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(T t) {
        deleteById(t.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        for(T item: iterable) {
            delete(item);
        }
    }

    @Override
    public void deleteAll() {
        items.clear();
    }
}
