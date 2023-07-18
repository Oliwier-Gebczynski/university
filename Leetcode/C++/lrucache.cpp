class LRUCache {
public:
    class Node {
    public:
        int key;
        int value;
        Node* next;
        Node* prev;

        Node(int key, int value) {
            this->key = key;
            this->value = value;
            next = nullptr;
            prev = nullptr;
        }
    };

    Node* head;
    Node* tail;
    int capacity;
    std::unordered_map<int, Node*> cache;

    LRUCache(int capacity) {
        this->capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head->next = tail;
        tail->prev = head;
    }

    void deleteNode(Node* node) {
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }

    void addToHead(Node* node) {
        node->next = head->next;
        node->prev = head;
        head->next->prev = node;
        head->next = node;
    }

    int get(int key) {
        if (cache.find(key) != cache.end()) {
            Node* node = cache[key];
            deleteNode(node);
            addToHead(node);
            return node->value;
        }
        return -1;
    }

    void put(int key, int value) {
        if (cache.find(key) != cache.end()) {
            Node* node = cache[key];
            node->value = value;
            deleteNode(node);
            addToHead(node);
        } else {
            Node* newNode = new Node(key, value);
            if (cache.size() == capacity) {
                Node* tailPrev = tail->prev;
                deleteNode(tailPrev);
                cache.erase(tailPrev->key);
                delete tailPrev;
            }
            addToHead(newNode);
            cache[key] = newNode;
        }
    }
};
