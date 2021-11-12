package trackcourse.springserver;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;
import trackcourse.core.FileHandlerApp;
import trackcourse.core.Subject;

@Service
public class TrackcouseService {
  // Dette blir på mange måter den nye AppControlleren.

  // Fagene:
  private Collection<Subject> subjects = new ArrayList<>();

  private final FileHandlerApp persistence;

  public TrackcouseService() {
    // leser fra fil og genererer filehandlerer med fagene
    persistence = new FileHandlerApp();
  }

  /**
   * Gets the Subjects.
   *
   * @return the subjects
   */
  protected Collection<Subject> getSubjects() {
    return persistence.getSubjects();
  }

}
