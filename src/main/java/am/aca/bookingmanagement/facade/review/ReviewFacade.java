package am.aca.bookingmanagement.facade.review;

import am.aca.bookingmanagement.dto.review.ReviewRegisterRequestDetails;
import org.hibernate.tool.schema.ast.SqlScriptParserException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public interface ReviewFacade {

    void registerReview(ReviewRegisterRequestDetails request);

}