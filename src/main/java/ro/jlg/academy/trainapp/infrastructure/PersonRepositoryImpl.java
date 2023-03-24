package ro.jlg.academy.trainapp.infrastructure;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.jlg.academy.trainapp.domain.Person;
import ro.jlg.academy.trainapp.domain.Train;

import java.util.List;
import java.util.Set;

@Repository
public class PersonRepositoryImpl {
   //dependency injection
    private MongoTemplate mongoTemplate;
    // dependency injection / inversion of control
    public PersonRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void save(final Person person) {
        this.mongoTemplate.save(person);
    }

    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id) );

        this.mongoTemplate.remove(query, Person.class);
    }

    public List<Person> findAll() {
        return this.mongoTemplate.findAll(Person.class);
    }

    public Person findById(final String id) {
        return this.mongoTemplate.findById(id, Person.class);
    }

    public List<Person> findAllByIds(final Set<String> personIds) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").in(personIds));
        return this.mongoTemplate.find(query, Person.class);
    }

}




