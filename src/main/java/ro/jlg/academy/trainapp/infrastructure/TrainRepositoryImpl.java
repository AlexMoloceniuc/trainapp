package ro.jlg.academy.trainapp.infrastructure;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.jlg.academy.trainapp.domain.Train;

import java.util.List;

@Repository
public class TrainRepositoryImpl {

    private MongoTemplate mongoTemplate;

    public TrainRepositoryImpl( MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void save(final Train train) {
        this.mongoTemplate.save(train);
    }

    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        this.mongoTemplate.remove(query, Train.class);
    }

    public List<Train> findAll() {
        return this.mongoTemplate.findAll(Train.class);
    }

    public Train findById(final String id) {
        return this.mongoTemplate.findById(id, Train.class);
    }

    public List<Train> findByDeparturePlace(final String departure) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("departure").is(departure));
        return this.mongoTemplate.find(query, Train.class);
    }

    public List<Train> findByDestinationPlace(final String destination) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("destination").is(destination));
        return this.mongoTemplate.find(query, Train.class);
    }

    public List<Train> findByPassengerId(final String passengerId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("passengerIds").in(passengerId));

        return this.mongoTemplate.find(query, Train.class);
    }

}
