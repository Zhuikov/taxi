package taxiApp.repository;

import taxiApp.Exceptions.NoEntityException;
import taxiApp.core.CV;

import java.sql.*;

// singleton
public class CVRepository extends TaxiItemRepository<CV> {

    private static CVRepository cvRepository = null;
    private static final String INSERT_STMT =
            "insert into CVs (`name`, surname, phone, experience, `read`) values (?,?,?,?,?)";
    private static final String UPDATE_STMT =
            "update CVs set `name`=?, surname=?, phone=?, experience=?, `read`=? where id=?";
    private static final String DELETE_STMT =
            "delete from CVs where id=?";
    private static final String SELECT_STMT =
            "select id, `name`, surname, phone, experience, `read` from CVs where id=?";
    private CVRepository() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("ERROR: Cannot create CVRepository");
            e.printStackTrace();
        }
    }

    public static CVRepository getInstance() {
        if (cvRepository == null)
            cvRepository = new CVRepository();
        return cvRepository;
    }

    @Override
    public long add(CV entity) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getPhone());
            statement.setInt(4, entity.getExperience());
            statement.setBoolean(5, false);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating CV failed, no ID obtained.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR CVRepository::create (id = " + entity + ")");
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_STMT)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR CVRepository::deleteById (id = " + id + ")");
            e.printStackTrace();
        }
    }

    @Override
    public CV getById(Long id) throws NoEntityException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_STMT)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long userId = rs.getLong("userId");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phone = rs.getString("phone");
                int experience = rs.getInt("experience");
                boolean read = rs.getBoolean("read");
                CV cv = new CV(userId, name, surname, phone, experience, read);
                cv.setId(rs.getLong("id"));
                return cv;
            }
        } catch (SQLException e) {
            System.out.println("ERROR CVRepository::getById (id = " + id + ")");
            e.printStackTrace();
        }
        throw new NoEntityException(id);
    }

    @Override
    public void update(CV entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STMT)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getPhone());
            statement.setInt(4, entity.getExperience());
            statement.setBoolean(5, entity.isShown());
            statement.setLong(6, entity.getId());
            System.out.println("QUERY = " + statement);
            int a = statement.executeUpdate();
            System.out.println("a = " + a);
        } catch (SQLException e) {
            System.out.println("ERROR CVRepository::update (id = " + entity.getId() + ")");
            e.printStackTrace();
        }
    }
}
