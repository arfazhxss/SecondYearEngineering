
package lab06;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

class TodoApplicationTest {

    private TodoApplication todoApp;
    private PersonService personServiceMock;
    private TodoService todoServiceMock;

    private final String userName = "SomeUser";
    private final Long userID = 1L;
    private final List<String> todos = List.of("Wake up", "Test the code", "Celebrate the victory!");

    @BeforeEach
    void setUp() {
        personServiceMock = mock(PersonService.class);
        todoServiceMock = mock(TodoService.class);
        todoApp = new TodoApplication(todoServiceMock, personServiceMock);
    }
    @Test
    void addTodo() {
        // Ensure that it's possible to add a todo to the app, and that the correct methods are called
        when(personServiceMock.findUsernameById(userID)).thenReturn(userName);
        when(todoServiceMock.addTodo(userName, anyString())).thenReturn(true);

        boolean result = todoApp.addTodo(userID, "New Todo");

        assertTrue(result);
        verify(personServiceMock).findUsernameById(userID);
        verify(todoServiceMock).addTodo(userName, "New Todo");
    }

    @Test
    void retrieveTodos() {
        // add multiple todos to the app, and retrieve a strict subset of them using a substring.
        when(personServiceMock.findUsernameById(userID)).thenReturn(userName);
        when(todoServiceMock.retrieveTodos(userName)).thenReturn(todos);

        List<String> result = todoApp.retrieveTodos(userID, "code");
        assertEquals(1, result.size());
        assertEquals("Test the code", result.get(0));
        verify(personServiceMock).findUsernameById(userID);
        verify(todoServiceMock).retrieveTodos(userName);
    }

    @Test
    void completeAllWithNoTodos() {
       // confirm that the appropriate behaviour occurs when there are no todos being tracked by the app
        when(personServiceMock.findUsernameById(userID)).thenReturn(userName);
        when(todoServiceMock.retrieveTodos(userName)).thenReturn(List.of());

        todoApp.completeAllTodos(userID);

        verify(personServiceMock).findUsernameById(userID);
        verify(todoServiceMock).retrieveTodos(userName);
        verifyNoInteractions(todoServiceMock);
    }

    @Test
    void completeAllWithThreeTodos() {
        // confirm that the appropriate behaviour occurs when there are three todos being tracked by the app
        when(personServiceMock.findUsernameById(userID)).thenReturn(userName);
        when(todoServiceMock.retrieveTodos(userName)).thenReturn(todos);

        todoApp.completeAllTodos(userID);

        verify(personServiceMock).findUsernameById(userID);
        verify(todoServiceMock).retrieveTodos(userName);
        verify(todoServiceMock, times(3)).completeTodo(anyString());
    }
}