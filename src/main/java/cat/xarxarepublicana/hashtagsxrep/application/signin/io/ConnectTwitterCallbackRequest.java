package cat.xarxarepublicana.hashtagsxrep.application.signin.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ConnectTwitterCallbackRequest {

  String oauthToken;
  String oauthVerifier;
  String denied;
}
