package marquez.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.net.URI;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import marquez.api.models.DataSourcesResponse;
import marquez.db.fixtures.AppWithPostgresRule;
import org.junit.ClassRule;
import org.junit.Test;

public class DataSourceIntegrationTest {

  @ClassRule public static final AppWithPostgresRule APP = new AppWithPostgresRule();

  @Test
  public void testListDatasourcesWithEmptyResultSet() {
    final Response res =
        APP.client()
            .target(URI.create("http://localhost:" + APP.getLocalPort()))
            .path("/api/v1/datasources")
            .request(MediaType.APPLICATION_JSON)
            .get();
    assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());

    DataSourcesResponse responseBody = res.readEntity(DataSourcesResponse.class);
    assertThat(responseBody.getDataSources().isEmpty());
  }
}